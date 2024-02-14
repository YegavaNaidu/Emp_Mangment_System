import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegisterComponent } from './user-register/user-register.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { OpenPageComponent } from './open-page/open-page.component';

const routes: Routes = [
  { path: '', component: OpenPageComponent},
  { path: 'register', component: UserRegisterComponent},
  { path: 'login', component:LoginPageComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
