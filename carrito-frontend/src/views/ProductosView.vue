<template>
  <div class="container">
    <h2>Productos</h2>
    <form @submit.prevent="crear">
      <input v-model="form.nombre" placeholder="Nombre" required />
      <input v-model="form.descripcion" placeholder="Descripción" required />
      <input v-model.number="form.precio" placeholder="Precio" type="number" required />
      <input v-model.number="form.stock" placeholder="Stock" type="number" required />
      <button type="submit">Crear Producto</button>
    </form>
    <table>
      <thead>
        <tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Stock</th></tr>
      </thead>
      <tbody>
        <tr v-for="p in productos" :key="p.id">
          <td>{{ p.id }}</td><td>{{ p.nombre }}</td><td>{{ p.precio }}</td><td>{{ p.stock }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
const API = 'http://localhost:8080/api/productos'
export default {
  data() {
    return {
      productos: [],
      form: { nombre: '', descripcion: '', precio: 0, stock: 0 }
    }
  },
  mounted() { this.listar() },
  methods: {
    async listar() {
      const res = await fetch(API)
      this.productos = await res.json()
    },
    async crear() {
      await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.form)
      })
      this.form = { nombre: '', descripcion: '', precio: 0, stock: 0 }
      this.listar()
    }
  }
}
</script>