import { INavData } from '@coreui/angular';

export const AdminnavItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer'
  },
  {
    name: 'Categories',
    url: '/base',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'Ajouter',
        url: '/Add-Category',
        icon: 'icon-puzzle'
      },
      {
        name: 'list',
        url: '/List-Category',
        icon: 'icon-puzzle'
      }
    ]
  },
  {
    divider: true
  },
  {
    name: 'Entreprises',
    url: '/base',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'list',
        url: '/List-Entreprise',
        icon: 'icon-puzzle'
      },
      {
        name: 'Demande de modification',
        url: '/Demandes-Entreprise',
        icon: 'icon-puzzle'
      }
    ]
  },
  {
    divider: true
  },
  {
    name: 'Clients',
    url: '/base',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'list',
        url: '/List-Client',
        icon: 'icon-puzzle'
      },
    ]
  },

];
