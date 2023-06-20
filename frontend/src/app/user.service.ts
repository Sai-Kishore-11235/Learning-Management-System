import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  validateUser(user:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = "http://65.0.85.115:8082/user-service/auth/v1/validateUser"
    return this.http.post(url,user,{headers})
  }
  loginUser(user:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = "http://65.0.85.115:8082/user-service/auth/v1/login"
    return this.http.post(url,user,{headers})
  }
  registerUser(user:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = "http://65.0.85.115:8082/user-service/auth/v1//user/addUser"
    return this.http.post(url,user,{headers})
  }
}
