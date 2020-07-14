import { Component, OnInit } from '@angular/core';
import { OnlineTestService } from '../online-test.service';
import { OnlineTest } from '../online-test';
import { Router } from '@angular/router';
import { ResultComponent } from '../result/result.component';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})

export class ReviewComponent implements OnInit {
 
  private quizzes:OnlineTest[];
  private attemptCount:number;
  constructor(private service:OnlineTestService,private router: Router) { }

  ngOnInit() {
    this.service.getAllQuesAns().subscribe(res => {
      this.quizzes=res;
      this.attemptCount=this.service.attemptCount;
    });
  }

  onSubmit()
  {
    this.service.attemptCount=0;
    for(let i=0 ;i<this.quizzes.length;i++)
    {
      this.quizzes[i].userAnswer=null;
      this.service.updateUserAnswer(this.quizzes[i].id, this.quizzes[i])
     .subscribe(data => console.log(data), error => console.log(error));
    }
    this.router.navigate(['/quiz']);
  }

}
