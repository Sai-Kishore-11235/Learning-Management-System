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
    // const url = "http://localhost:8082/user-service/api/v1/validateUser"
    user = {
      "title" : "Python 22",
      "hours": 22,
      "technology" : "Python"
  }
    const url = "http://localhost:8082/course-service/api/v1.0/lms/courses/add/test12"
    return this.http.post(url,user,{headers})
  }
}
