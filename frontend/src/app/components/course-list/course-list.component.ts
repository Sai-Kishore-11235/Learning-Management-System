import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { CourseService } from 'src/app/course.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courseList:any[];
  adminFlag: boolean = false;
  constructor(private courseService:CourseService,private authservice: AuthService,private router:Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("user")){
      this.authservice.login(sessionStorage.getItem("user")??"")
      if(sessionStorage.getItem("user")=='admin'){
        this.adminFlag = true;
      }
      this.getAllCourses()
    }
    else{
      this.router.navigate(['logout'])
    }
    
  }

  deleteCourse(course:any){
    this.courseService.deleteCourse(course).subscribe(res=>{
  if(res){
    console.log(res)
    this.courseList = [];
    this.getAllCourses()
  }      
    })

  }
  getAllCourses(){
    this.courseService.getAllCourses().subscribe(res=>{
      this.courseList = res;
      console.log(this.courseList)
    })
  }

}
