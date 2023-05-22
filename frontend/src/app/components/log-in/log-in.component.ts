import { RepositionScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  loginForm: FormGroup;
  user:any;
  errorMessage: string=""
  
  constructor(private userService:UserService,private fb: FormBuilder,private _snackBar: MatSnackBar) { }

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
        sessionStorage.setItem("user",this.user.username)
        localStorage.setItem("token",response.token)
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
