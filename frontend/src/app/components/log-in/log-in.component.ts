import { RepositionScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { UserService } from 'src/app/user.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  loginForm: FormGroup;
  user:any;
  errorMessage: string=""
  
  constructor(private userService:UserService,private fb: FormBuilder,private _snackBar: MatSnackBar, private route: ActivatedRoute,
    private router: Router,private authservice:AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ["", Validators.required],
      password: ["", Validators.required]
    });
  }
  onValidate(){
    if (this.loginForm.valid) {
     
      this.user = this.loginForm.value;
       this.userService.validateUser(this.user).subscribe((response )=> {
      console.log(response)
      if(response.hasusername){
        this.errorMessage=""
        this.loginForm.reset();
        this.loginUser();
      }
      else{
        this.errorMessage = "Invalid User"
      }
    },(error)=>{
      console.error(error)
    }
    );
    }
   
  }
  loginUser(){
    this.userService.loginUser(this.user).subscribe((response)=>{
      if(response.message.includes("user successfully logged in")){
        this.authservice.login(this.user.username)
        // sessionStorage.setItem("user",this.user.username)
        localStorage.setItem("token",response.token)
        // displayLogout()
        this.router.navigate(['/courseList'])
      }

    },(error)=>{
      // this.errorMessage =
      this._snackBar.open("Invalid Credentials", "Login",{
        duration: 3000
      });

      console.error(error)
    })
  }
}
