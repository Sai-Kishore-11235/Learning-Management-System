import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  user:any;
  errorMessage: string=""
  Roles: any = ['Admin', 'Author', 'Reader'];
  constructor(private userService:UserService,private fb: FormBuilder,private _snackBar: MatSnackBar, private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ["", Validators.required],
      userEmail: ["", [Validators.required,Validators.email]],
      password: ["", [Validators.required,this.passwordPatternValidator()]]
    });
  };
  passwordPatternValidator(){
    return (control: AbstractControl): ValidationErrors | null =>{
      const password = control.value;
      const pattern = /^(?=.*[A-Za-z])(?=.*\d).{8,}$/;
      if(!pattern.test(password)){
        return {passwordPattern:true};
      }
      return null;
    };
  }
  onValidate(){
    if (this.registerForm.valid) {
      this.user = this.registerForm.value;
      this.userService.registerUser(this.user).subscribe((response )=> {
    //  console.log(response)
     if(response.userAdded){
       this.errorMessage=""
       this.registerForm.reset()
       this._snackBar.open("User Registered Successfully Please login", "Register",{
        duration: 3000
      });

       this.router.navigate(['/login'])
    
     }
     else{
       this.errorMessage = "Invalid User"
     }
   },(error)=>{
    this.errorMessage = "User Already Exists"
     console.error(error)
   }
   );
    }
  }
}