import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QuizComponent } from './quiz/quiz.component';
import { ResultComponent } from './result/result.component';
import { ReviewComponent } from './review/review.component';
import { OnlineTestService } from './online-test.service';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UpdateUserAnswerComponent } from './update-user-answer/update-user-answer.component';

@NgModule({
  declarations: [
    AppComponent,
    QuizComponent,
    ResultComponent,
    ReviewComponent,
    UpdateUserAnswerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers:[ OnlineTestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
