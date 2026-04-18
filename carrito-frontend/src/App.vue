<template>
  <div>
    <nav>
      <RouterLink to="/usuarios">Usuarios</RouterLink>
      <RouterLink to="/productos">Productos</RouterLink>
      <RouterLink to="/facturas">Facturas</RouterLink>
      <RouterLink to="/detalles">Detalles</RouterLink>
    </nav>
    <main>
      <RouterView />
    </main>
  </div>
</template>

<style>
* { box-sizing: border-box; margin: 0; padding: 0; }
body { font-family: Arial, sans-serif; background: #f4f4f4; }
nav {
  background: #2c3e50;
  padding: 15px 30px;
  display: flex;
  gap: 20px;
}
nav a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  padding: 8px 16px;
  border-radius: 6px;
  transition: background 0.2s;
}
nav a:hover, nav a.router-link-active {
  background: #42b883;
}
main { padding: 30px; }
</style>