import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {
  
  title = 'Learning-Management-System';
  userFlag : boolean =false;
  constructor(private authservice:AuthService) { }

  ngOnInit(): void {
    this.authservice.isLoggedIn$.subscribe(isLoggedIn =>{
      this.userFlag = isLoggedIn
    })
  }
  

}
