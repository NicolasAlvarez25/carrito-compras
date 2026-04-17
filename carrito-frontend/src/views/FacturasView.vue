<template>
  <div class="container">
    <h2>Facturas</h2>
    <form @submit.prevent="crear">
      <input v-model.number="form.usuarioId" placeholder="ID del Usuario" type="number" required />
      <input v-model.number="form.total" placeholder="Total" type="number" required />
      <button type="submit">Crear Factura</button>
    </form>
    <table>
      <thead>
        <tr><th>ID</th><th>Usuario ID</th><th>Fecha</th><th>Total</th></tr>
      </thead>
      <tbody>
        <tr v-for="f in facturas" :key="f.id">
          <td>{{ f.id }}</td><td>{{ f.usuario?.id }}</td><td>{{ f.fecha }}</td><td>{{ f.total }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
const API = 'http://localhost:8080/api/facturas'
export default {
  data() {
    return {
      facturas: [],
      form: { usuarioId: '', total: 0 }
    }
  },
  mounted() { this.listar() },
  methods: {
    async listar() {
      const res = await fetch(API)
      this.facturas = await res.json()
    },
    async crear() {
      await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          usuario: { id: this.form.usuarioId },
          fecha: new Date().toISOString(),
          total: this.form.total
        })
      })
      this.form = { usuarioId: '', total: 0 }
      this.listar()
    }
  }
}
</script>