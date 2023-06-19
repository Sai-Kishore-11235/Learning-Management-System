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
    const url = "http://13.232.144.85:8082/course-service/api/v1.0/lms/courses/getall"
    return this.http.get(url,{headers})
  }

  saveCourse(course:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://13.232.144.85:8082/course-service/api/v1.0/lms/courses/add/${course.title}`
    return this.http.post(url,course)
  }

  deleteCourse(course:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://13.232.144.85:8082/course-service/api/v1.0/lms/courses/delete/${course.title}`
    return this.http.delete(url,{headers})
  }
  getByTechnology(technology:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://13.232.144.85:8082/course-service/api/v1.0/lms/courses/info/${technology}`
    return this.http.get(url,{headers})
  }
  getByTechnologyAndMinMaxhours(data:any):Observable<any>{
    let headers = new HttpHeaders({'Content-Type': 'text/plain'})
    // headers.append('Access-Control-Allow-Origin', '*')
    const url = `http://13.232.144.85:8082/course-service/api/v1.0/lms/courses/get/${data.technology}/${data.minHours}/${data.maxHours}`
    return this.http.get(url,{headers})
  }
}

