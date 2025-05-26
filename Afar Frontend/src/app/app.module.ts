import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

import { IconModule, IconSetModule, IconSetService } from '@coreui/icons-angular';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from './app.component';

// Import containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {JwtHelperService, JwtInterceptor, JwtModule} from '@auth0/angular-jwt';
import {UserService} from './user.service';
import {AuthGuard} from './auth.guard';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import {ToastNoAnimationModule} from 'ngx-toastr';
import { ListCategoryComponent } from './views/list-category/list-category.component';
import { AddCategoryComponent } from './views/add-category/add-category.component';
import { ListEntrepriseComponent } from './views/list-entreprise/list-entreprise.component';
import { DemandeEntrepriseComponent } from './views/demande-entreprise/demande-entreprise.component';
import { ListClientComponent } from './views/list-client/list-client.component';
import {AuthInterceptor} from './AuthInterceptor';
import {BsModalRef, BsModalService, ModalModule} from 'ngx-bootstrap/modal';
import { RequestEditEntrepriseComponent } from './views/request-edit-entreprise/request-edit-entreprise.component';
import {ListCommandsComponent} from './views/base/list-commands/list-commands.component';
import {collapseAnimation} from 'ngx-bootstrap/collapse/collapse-animations';
import {CollapseModule} from 'ngx-bootstrap/collapse';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    IconModule,
    IconSetModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),

    JwtModule.forRoot({
      config: {
        tokenGetter: function tokenGetter() {
          return localStorage.getItem('currentUser');
        }
      }
    }),
    ToastNoAnimationModule.forRoot(),
    CollapseModule,

  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    RegisterComponent,
    WelcomePageComponent,
    ListCategoryComponent,
    AddCategoryComponent,
    ListEntrepriseComponent,
    DemandeEntrepriseComponent,
    ListClientComponent,
    RequestEditEntrepriseComponent,
    ListCommandsComponent
  ],

  providers: [
    UserService , AuthGuard , BsModalRef,
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    IconSetService,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
