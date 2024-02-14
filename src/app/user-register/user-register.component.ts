import { Component } from '@angular/core';
import { UserModule } from '../module/module.user';
import { FormControl, FormGroup, UntypedFormControl } from '@angular/forms';
import { LoginServicesService } from '../login-services/login-services.service';


@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

 constructor(private user : UserModule, private registerservice : LoginServicesService){}

user_register_dtls = new FormGroup({
    username : new UntypedFormControl(''),
    email : new UntypedFormControl(''),
    password : new UntypedFormControl('')
})

UserRegister(){
  this.user.username =this.user_register_dtls.value.username;
  this.user.email = this.user_register_dtls.value.email;
  this.user.password = this.user_register_dtls.value.password;
  console.log(this.user_register_dtls.value);
  this.registerservice.register(this.user);

}

}
