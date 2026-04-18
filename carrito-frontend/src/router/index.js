import { createRouter, createWebHistory } from 'vue-router'
import UsuariosView from '../views/UsuariosView.vue'
import ProductosView from '../views/ProductosView.vue'
import FacturasView from '../views/FacturasView.vue'
import DetallesView from '../views/DetallesView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/usuarios' },
    { path: '/usuarios', component: UsuariosView },
    { path: '/productos', component: ProductosView },
    { path: '/facturas', component: FacturasView },
    { path: '/detalles', component: DetallesView },
  ],
})

export default router