import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http:HttpClient) { }

  getAllCourses():Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = "http://localhost:8082/course-service/api/v1.0/lms/courses/getall"
    return this.http.get(url,{headers})
  }

  saveCourse(course:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://localhost:8082/course-service/api/v1.0/lms/courses/add/${course.title}`
    return this.http.post(url,course)
  }

  deleteCourse(course:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://localhost:8082/course-service/api/v1.0/lms/courses/delete/${course.title}`
    return this.http.delete(url,{headers})
  }
}

