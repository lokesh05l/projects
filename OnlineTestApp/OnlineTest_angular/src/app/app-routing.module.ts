import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QuizComponent } from './quiz/quiz.component';
import { ResultComponent } from './result/result.component';
import { ReviewComponent } from './review/review.component';
import { UpdateUserAnswerComponent } from './update-user-answer/update-user-answer.component';


const routes: Routes = [
  {path: 'quiz',component:QuizComponent},
  { path: 'quiz/:id', component: UpdateUserAnswerComponent },
  {path: 'result', component:ResultComponent},
  {path: 'review', component:ReviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
