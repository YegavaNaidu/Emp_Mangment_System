import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { UserModule } from '../module/module.user';
@Injectable({
  providedIn: 'root'
})
export class LoginServicesService {

  constructor(public http: HttpClient , private user1: UserModule ) { }


  private _url ="http://localhost:8080";

 headerDict = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
  // 'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
  // 'Access-Control-Allow-Origin': '*'
}

 headerOptions = new HttpHeaders(this.headerDict);

  VerifyCrds(username: string,password: string){

    let input1= 'hello';
    let Lparms = new HttpParams();
    Lparms.append("UserName",username);
    Lparms.append("Password",password);
    return this.http.post(this._url+'/sample',input1)
    .subscribe(res => console.log(res));
  }

  register(user: UserModule ){
    return this.http.post(this._url+'/register',user,{headers: this.headerDict})
    .subscribe(res => console.log(res));
  }
}
