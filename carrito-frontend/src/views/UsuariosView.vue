<template>
  <div class="container">
    <h2>Usuarios</h2>
    <form @submit.prevent="crear">
      <input v-model="form.nombre" placeholder="Nombre" required />
      <input v-model="form.email" placeholder="Email" required />
      <input v-model="form.password" placeholder="Password" required />
      <button type="submit">Crear Usuario</button>
    </form>
    <table>
      <thead>
        <tr><th>ID</th><th>Nombre</th><th>Email</th></tr>
      </thead>
      <tbody>
        <tr v-for="u in usuarios" :key="u.id">
          <td>{{ u.id }}</td><td>{{ u.nombre }}</td><td>{{ u.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
const API = 'http://localhost:8080/api/usuarios'
export default {
  data() {
    return {
      usuarios: [],
      form: { nombre: '', email: '', password: '' }
    }
  },
  mounted() { this.listar() },
  methods: {
    async listar() {
      const res = await fetch(API)
      this.usuarios = await res.json()
    },
    async crear() {
      await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.form)
      })
      this.form = { nombre: '', email: '', password: '' }
      this.listar()
    }
  }
}
</script>