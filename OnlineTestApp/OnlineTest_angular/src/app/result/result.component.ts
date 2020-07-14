import { Component, OnInit } from '@angular/core';
import { OnlineTestService } from '../online-test.service';
import { Router } from '@angular/router';
import { OnlineTest } from '../online-test';
import { stringify } from 'querystring';
import { ReviewComponent } from '../review/review.component';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})

export class ResultComponent implements OnInit {

  private quizzes: OnlineTest[];
  public count:any=0;
  constructor(private service: OnlineTestService, private router: Router) { 

  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.service.getAllQuesAns().subscribe(res => {
      this.quizzes=res;
      this.checkResults();
    });   
  }  

  checkResults()
  {
    for(let i=0;i<this.quizzes.length;i++)
    {
      if(this.quizzes[i].userAnswer.toLowerCase().replace(/\s/g,"") === this.quizzes[i].correctAnswer.toLowerCase().replace(/\s/g,"") )
        this.count++;
     }
  }

  onSubmit()
  {
    this.service.attemptCount++;
    if(this.count<(0.6 * this.quizzes.length))
    {
      for(let i=0 ;i<this.quizzes.length;i++)
      {
        this.quizzes[i].userAnswer=null;
        this.service.updateUserAnswer(this.quizzes[i].id, this.quizzes[i])
        .subscribe(data => console.log(data), error => console.log(error));
      }
      this.router.navigate(['/quiz']);
    }
    else
      this.router.navigate(['/review']);
    
  }
}
