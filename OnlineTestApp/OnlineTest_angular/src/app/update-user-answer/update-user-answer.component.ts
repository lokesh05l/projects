import { Component, OnInit } from '@angular/core';
import { OnlineTest } from '../online-test';
import { OnlineTestService } from '../online-test.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-user-answer',
  templateUrl: './update-user-answer.component.html',
  styleUrls: ['./update-user-answer.component.css']
})

export class UpdateUserAnswerComponent implements OnInit {

  id:number;
  quiz:OnlineTest;

  constructor(private route: ActivatedRoute,private router: Router,private service: OnlineTestService) { 

  }

  ngOnInit() {
    this.quiz = new OnlineTest();

    this.id = this.route.snapshot.params['id'];
    
    this.service.getQuizById(this.id)
      .subscribe(data => {
        console.log(data)
        this.quiz = data;
      }, error => console.log(error));
  }

  updateUserAnswer() {
    this.service.updateUserAnswer(this.id, this.quiz)
      .subscribe(data => console.log(data), error => console.log(error));
    this.quiz = new OnlineTest();
    this.gotoQuiz();
  }

  onSubmit() {
    this.updateUserAnswer();    
  }

  gotoQuiz() {
    this.router.navigate(['/quiz']);
  }
}






