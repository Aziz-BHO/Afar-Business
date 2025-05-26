import { INavData } from '@coreui/angular';

export const EntreprisenavItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer'
  },
  {
    name: 'Publicites',
    url: '/publicities',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'add',
        url: '/publicities/add',
        icon: 'icon-puzzle'
      },
      {
        name: 'list',
        url: '/publicities/list',
        icon: 'icon-puzzle'
      }
    ]
  },
  {
    name: 'Products',
    url: '/products',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'add',
        url: '/products/add',
        icon: 'icon-puzzle'
      },
      {
        name: 'list',
        url: '/products/list',
        icon: 'icon-puzzle'
      }
    ]
  },
  {
    name: 'Commands',
    url: '/products',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'List',
        url: '/List-Commands',
        icon: 'icon-puzzle'
      }
    ]
  },
];
