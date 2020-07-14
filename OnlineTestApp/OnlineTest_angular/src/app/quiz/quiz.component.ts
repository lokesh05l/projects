import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { OnlineTestService } from '../online-test.service';
import { OnlineTest } from '../online-test';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {

  private quizzes: OnlineTest[];
  private count:number=0;
  private map:Map<number,number>=new Map();
  enabled:boolean=false;
  constructor(private service: OnlineTestService,private router: Router) { 

    }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.service.getAllQuesAns().subscribe(res => {
      this.quizzes=res;
    });
  }

  updateUserAnswer(id:number){

    this.router.navigate(['quiz', id]);
   }

   onSubmit()
   {
     this.router.navigate(['/result']);
   }
}


