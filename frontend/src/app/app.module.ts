import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'; 
import { UserService } from './user.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CourseListComponent } from './components/course-list/course-list.component';
import { LogOutComponent } from './components/log-out/log-out.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CardModule } from 'primeng/card';
import { CourseService } from './course.service';
import { AddCourseComponent } from './add-course/add-course.component';
import { FilterDialogComponent } from './filter-dialog/filter-dialog.component';
import { InterceptorService } from './interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    CourseListComponent,
    LogOutComponent,
    NavbarComponent,
    AddCourseComponent,
    FilterDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FormsModule, 
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    FlexLayoutModule,
    CardModule,
 

  ],
  providers: [ {
     provide: HTTP_INTERCEPTORS,
     useClass: InterceptorService,
     multi: true
    },
   UserService,CourseService ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
