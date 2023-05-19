import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  username: String = ""
  userPass: String = ""
  user:any ={}
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  onValidate(){
    console.log(this.username,this.userPass)
    this.user["username"]= this.username;
    this.user["password"] = this.userPass
    this.userService.validateUser(this.user).subscribe((response )=> {
      console.log(response)
    },(error)=>{
      console.error(error)
    }
    );
  }

}
