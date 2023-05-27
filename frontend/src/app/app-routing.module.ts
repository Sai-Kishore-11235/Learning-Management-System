import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCourseComponent } from './add-course/add-course.component';
import { CourseListComponent } from './components/course-list/course-list.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { LogOutComponent } from './components/log-out/log-out.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'courseList', component: CourseListComponent },
  { path: 'courseList/course/add', component: AddCourseComponent },
  { path: 'logout', component: LogOutComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
