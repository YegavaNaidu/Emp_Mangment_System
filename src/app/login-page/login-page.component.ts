import { Component } from '@angular/core';
import { FormControl, FormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { LoginServicesService } from '../login-services/login-services.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  loginDetails = new FormGroup({
  userName : new UntypedFormControl('',[Validators.required]),
  password : new UntypedFormControl('',[Validators.required])
  })

  constructor(private Login: LoginServicesService){

  }

  get userName(){
    return this.loginDetails.get('userName');
  }

  get password(){
    return this.loginDetails.get('password');
  }

  onsumbit(){
      this.Login.VerifyCrds(this.loginDetails.value.userName,this.loginDetails.value.password);
  }


}


