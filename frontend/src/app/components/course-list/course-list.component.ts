import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';
import { CourseService } from 'src/app/course.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courseList:any;
  constructor(private courseService:CourseService,private authservice: AuthService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("user")){
      this.authservice.login(sessionStorage.getItem("user")??"")
    }
    this.courseService.getAllCourses().subscribe(res=>{
      this.courseList = res;
      console.log(this.courseList)
    })
  }

}
