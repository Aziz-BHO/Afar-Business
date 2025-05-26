import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import { P404Component } from './views/error/404.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import {AuthGuard} from './auth.guard';
import {WelcomePageComponent} from './welcome-page/welcome-page.component';

// @ts-ignore
// @ts-ignore
export const routes: Routes = [
  {
    path: '',
    component: WelcomePageComponent,
    data: {
      title: 'Welcome Page'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    canActivate: [AuthGuard],
    children: [
      {
        path: 'publicities',
        loadChildren: () => import('./views/base/base.module').then(m => m.BaseModule)
      },
      {
        path: 'products',
        loadChildren: () => import('./views/product/product.module').then(m => m.ProductModule)
      },
      {
        path: 'buttons',
        loadChildren: () => import('./views/buttons/buttons.module').then(m => m.ButtonsModule)
      },
      {
        path: 'charts',
        loadChildren: () => import('./views/chartjs/chartjs.module').then(m => m.ChartJSModule)
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./views/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'notifications',
        loadChildren: () => import('./views/notifications/notifications.module').then(m => m.NotificationsModule)
      },
      {
        path: 'theme',
        loadChildren: () => import('./views/theme/theme.module').then(m => m.ThemeModule)
      },
      {
        path: 'widgets',
        loadChildren: () => import('./views/widgets/widgets.module').then(m => m.WidgetsModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'List-Category',
        loadChildren: () => import('./views/list-category/list-category.module').then(m => m.ListCategoryModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'Add-Category',
        loadChildren: () => import('./views/add-category/add-category.module').then(m => m.AddCategoryModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'List-Entreprise',
        loadChildren: () => import('./views/list-entreprise/list-entreprise.module').then(m => m.ListEntrepriseModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'List-Client',
        loadChildren: () => import('./views/list-client/list-client.module').then(m => m.ListClientModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'Request-Edit',
        loadChildren: () => import('./views/request-edit-entreprise/request-edit-entreprise.module').then(m => m.RequestEditEntrepriseModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'Demandes-Entreprise',
        loadChildren: () => import('./views/demande-entreprise/demande-entreprise.module').then(m => m.DemandeEntrepriseModule) ,
        canActivate: [AuthGuard],
      },
      {
        path: 'List-Commands',
        loadChildren: () => import('./views/base/list-commands/list-commands.module').then(m => m.ListCommandsModule) ,
        canActivate: [AuthGuard],
      },
    ]
  },

  { path: '**', component: P404Component }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
