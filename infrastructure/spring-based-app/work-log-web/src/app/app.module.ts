import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NewWorkerComponent } from './new-worker/new-worker.component';
import { EditWorkerComponent } from './edit-worker/edit-worker.component';
import { LoginWorkerComponent } from './login-worker/login-worker.component';
import { WorkLogListComponent } from './work-log-list/work-log-list.component';
import { WorkLogComponent } from './work-log/work-log.component';
import { NewWorkLogComponent } from './new-work-log/new-work-log.component';

@NgModule({
  declarations: [
    AppComponent,
    NewWorkerComponent,
    EditWorkerComponent,
    LoginWorkerComponent,
    WorkLogListComponent,
    WorkLogComponent,
    NewWorkLogComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
        { path: '', component: LoginWorkerComponent},
        { path: 'work-log-page', component: WorkLogListComponent},
        { path: 'create-worker-page', component: NewWorkerComponent},
        { path: 'edit-worker-page', component: EditWorkerComponent},
        { path: 'new-work-log-page', component: NewWorkLogComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
