<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'CarComponent',
  setup() {
    const carData = ref([]);

    axios.get('http://localhost:8080/cars')
      .then(response => {
        carData.value = response.data;
        console.log(carData.value);
      })
      .catch(error => {
        console.error(error);
      });

    return { carData };
  }
});
</script>

<template>
<div>
  <ul>
    <li v-for="car in carData" :key="car.id">
      <h3>{{ car.brand.name }}</h3>
      <p>{{car.brand.model.name}}</p>
      <p>{{car.brand.model.version.name}}</p>
      <img :src="car.image" alt="Car Image" class="car-image">
    </li>
  </ul>
</div>
</template>
<style scoped>
.car-image {
  width: 200px;
  height: auto;
}
</style>