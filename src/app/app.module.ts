import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { FormControl,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserModule } from './module/module.user';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { OpenPageComponent } from './open-page/open-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    UserRegisterComponent,
    HeaderComponent,
    FooterComponent,
    OpenPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
