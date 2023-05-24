import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-log-out',
  templateUrl: './log-out.component.html',
  styleUrls: ['./log-out.component.css']
})
export class LogOutComponent implements OnInit {

  constructor(private router:Router,private authservice:AuthService) { }

  ngOnInit(): void {
    this.authservice.logout()
    sessionStorage.clear();
    localStorage.clear(); 
    this.router.navigate(['/login'])
  }

}
