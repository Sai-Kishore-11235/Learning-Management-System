import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedInSubject.asObservable();
  constructor() { }
  login(user: string){
    sessionStorage.setItem('user',user)
    this.isLoggedInSubject.next(true)
  }
  logout(){
    sessionStorage.removeItem('user')
    this.isLoggedInSubject.next(false)
  }
  
}
