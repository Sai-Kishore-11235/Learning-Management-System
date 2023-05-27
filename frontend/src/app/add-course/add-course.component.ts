import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../course.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  courseAddForm: FormGroup;
  course:any;
  errorMessage: string=""
  constructor(private courseService:CourseService,private fb: FormBuilder,private _snackBar: MatSnackBar, private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    this.courseAddForm = this.fb.group({
      title: ["", Validators.required],
      technology: ["", Validators.required],
      hours: ["", Validators.required],
     
    });
  };
  
  onValidate(){
    if (this.courseAddForm.valid) {
      this.course = this.courseAddForm.value;
      this.courseService.saveCourse(this.course).subscribe((response )=> {
    //  console.log(response)
     if(response){
       this.errorMessage=""
       this.courseAddForm.reset()
       this._snackBar.open("Course Saved Successfully", "Course",{
        duration: 3000
      });

       this.router.navigate(['/courseList'])
    
     }
     else{
       this.errorMessage = "Error In Creation of course"
     }
   },(error)=>{
    this.errorMessage = "Error In Creation of course"
     console.error(error)
   }
   );
    }
  }
}
