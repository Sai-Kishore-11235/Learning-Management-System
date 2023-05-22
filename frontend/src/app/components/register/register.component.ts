import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  constructor(private userService:UserService,private fb: FormBuilder,private _snackBar: MatSnackBar) { }
  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ["", Validators.required],
      userEmail: ["", Validators.required],
      password: ["", Validators.required]
    });
  }

  onValidate(){
    console.log(this.registerForm.value)
  }
}