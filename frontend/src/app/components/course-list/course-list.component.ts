import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { CourseService } from 'src/app/course.service';
import { FilterDialogComponent } from 'src/app/filter-dialog/filter-dialog.component';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courseList:any[];
  adminFlag: boolean = false;
  searchInput: any =""
  filterTechnology="";
  minHours =0;
  maxHours=0;
  filterData: any;
  constructor(private courseService:CourseService,private authservice: AuthService, private _snackBar: MatSnackBar,private router:Router,public dialog: MatDialog) { }

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
  searchTechnology(){
    if(this.searchInput.trim()==""){
      this.getAllCourses()
    }
    else{
      this.courseService.getByTechnology(this.searchInput).subscribe(res=>{
        this.courseList = res;
        console.log(this.courseList)
  
      })
    }
    
  }
  filterTechnologyWithHours(){
    const dialogRef =  this.dialog.open(FilterDialogComponent,{
      data: {technology: this.filterTechnology, minHours: this.minHours,maxHours : this.maxHours},
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result)
      this.filterData = result
      this.filterByTechnologyAndMinMaxHours()
    
       });
  }
  filterByTechnologyAndMinMaxHours(){
    if(this.filterData.technology.trim()=="" && this.filterData.minHours ==0 && this.filterData.maxHours ==0){
      this.getAllCourses()
    }
    else{
      this.courseService.getByTechnologyAndMinMaxhours(this.filterData).subscribe(res=>{
        this.courseList = res;
        console.log(this.courseList)
  
      })
    }
    
  }

  registerCourse(){
    this._snackBar.open("Course Registered Successfully", "Course",{
      duration: 3000
    });
  }
  
}


