<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';
import Dialog from 'primevue/dialog';
import Select from 'primevue/select';

export default defineComponent({
  name: 'CarComponent',
  setup() {
    const visible = ref(false); 
    const selectedBrand = ref(null);
    const selectedModel = ref(null);
    const carData = ref([]);
    const brandData = ref([]);
    const modelData = ref([]);

    const fetchCars = async () => {
      try {
        const response = await axios.get('http://localhost:8080/cars');
        carData.value = response.data; 
      } catch (error) {
        console.error("Failed to fetch cars:", error);
      }
    };

    const fetchBrands = async () => {
      try {
      const response = await axios.get('http://localhost:8080/brands');
      brandData.value = response.data; 
      modelData.value = response.data.map(brand => brand.models);

      console.log("Brand Data:", brandData.value)
      console.log("Model Data:", modelData.value)
      } catch (error) {
      console.error("Failed to fetch brands:", error);
      }
    }

    const openDialogWithBrand = async (brand, model) => {
      selectedBrand.value = brand;
      selectedModel.value = model;

      console.log("Selected Brand:", selectedBrand.value)
      console.log("Selected Model:", selectedModel.value)
      visible.value = true
    }

    fetchCars(); 
    fetchBrands();

    return {
      visible,
      carData,
      brandData,
      modelData,
      selectedBrand,
      selectedModel,
      openDialogWithBrand,
    };
  },
});
</script>

<template>
<div>
  <ul>
    <li v-for="car in carData" :key="car.id" class="card">
      <h3>{{ car.brand.name }}</h3>
      <p>{{car.brand.model.name}}</p>
      <p>{{car.brand.model.version.name}}</p>
      <img :src="car.image" alt="Car Image" class="car-image">
     <div class="card flex justify-center">
        <Button label="Update" @click="openDialogWithBrand(car.brand.name, car.brand.model.name)" />
        <Dialog v-model:visible="visible" modal header="Edit Profile" :style="{ width: '25rem' }">
            <span class="text-surface-500 dark:text-surface-400 block mb-8">Update your car.</span>
            <div class="flex items-center gap-4 mb-4">
                <label for="brand" class="font-semibold w-24">Brand</label>
                <Select v-model="selectedBrand" :options="brandData" optionLabel="name" optionValue="name" class="w-full md:w-56"/>
            </div>
            <div class="flex items-center gap-4 mb-4">
                <label for="model" class="font-semibold w-24">Model</label>
                <Select v-model="selectedModel" :options="modelData" optionLabel="name" optionValue="" class="w-full md:w-56"/>
          </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Cancel" severity="secondary" @click="visible = false"></Button>
                <Button type="button" label="Save" @click="visible = false"></Button>
            </div>
        </Dialog>
    </div>
    </li>
  </ul>
</div>
</template>
<style scoped>
.car-image {
  width: 500px;
  height: auto;
}
</style>