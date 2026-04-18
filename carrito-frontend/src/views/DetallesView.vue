<template>
  <div class="container">
    <h2>Detalle Factura</h2>
    <form @submit.prevent="crear">
      <input v-model.number="form.facturaId" placeholder="ID Factura" type="number" required />
      <input v-model.number="form.productoId" placeholder="ID Producto" type="number" required />
      <input v-model.number="form.cantidad" placeholder="Cantidad" type="number" required />
      <input v-model.number="form.subtotal" placeholder="Subtotal" type="number" required />
      <button type="submit">Crear Detalle</button>
    </form>
    <table>
      <thead>
        <tr><th>ID</th><th>Factura ID</th><th>Producto ID</th><th>Cantidad</th><th>Subtotal</th></tr>
      </thead>
      <tbody>
        <tr v-for="d in detalles" :key="d.id">
          <td>{{ d.id }}</td><td>{{ d.factura?.id }}</td><td>{{ d.producto?.id }}</td><td>{{ d.cantidad }}</td><td>{{ d.subtotal }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
const API = 'http://localhost:8080/api/detalles'
export default {
  data() {
    return {
      detalles: [],
      form: { facturaId: '', productoId: '', cantidad: 0, subtotal: 0 }
    }
  },
  mounted() { this.listar() },
  methods: {
    async listar() {
      const res = await fetch(API)
      this.detalles = await res.json()
    },
    async crear() {
      await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          factura: { id: this.form.facturaId },
          producto: { id: this.form.productoId },
          cantidad: this.form.cantidad,
          subtotal: this.form.subtotal
        })
      })
      this.form = { facturaId: '', productoId: '', cantidad: 0, subtotal: 0 }
      this.listar()
    }
  }
}
</script>