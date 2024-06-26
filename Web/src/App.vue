<template>
  <div>
    <ul>
      <li v-for="car in carData" :key="car.id" class="card">
        <h3>{{ car.brand.name }}</h3>
        <p>{{ car.brand.model.name }}</p>
        <p>{{ car.brand.model.version.name }}</p>
        <img :src="car.image" alt="Car Image" class="car-image">
        <div class="card flex justify-center">
          <Button label="Update" @click="openDialogWithBrand(car.brand, car.brand.model, car.brand.model.version)" />
          <Dialog v-model:visible="visible" modal header="Edit Profile" :style="{ width: '25rem' }">
            <span class="text-surface-500 dark:text-surface-400 block mb-8">Update your car.</span>
            <div class="flex items-center gap-4 mb-4">
              <label for="brand" class="font-semibold w-24">Brand</label>
              <Select v-model="selectedBrand.id" :options="brandData" optionLabel="name" optionValue="id" class="w-full md:w-56" @change="handleChange"  highlightOnSelect/>
            </div>
            <div class="flex items-center gap-4 mb-4">
              <label for="model" class="font-semibold w-24">Model</label>
              <Select v-model="selectedModel.id" :options="modelFiltered" optionLabel="name"  optionValue="id" class="w-full md:w-56" @change="handleVersionChange"  highlightOnSelect/>
            </div>
            <div class="flex items-center gap-4 mb-4">
              <label for="version" class="font-semibold w-24">Version</label>
              <Select v-model="selectedVersion.id" :options="versionFiltered" optionLabel="name" optionValue="id" class="w-full md:w-56" highlightOnSelect/>
            </div>
            <div class="flex justify-end gap-2">
              <Button type="button" label="Cancel" severity="secondary" @click="visible = false" />
              <Button type="button" label="Save" @click="visible = false" />
            </div>
          </Dialog>
        </div>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';
import Dialog from 'primevue/dialog';
import Select from 'primevue/select';
import Button from 'primevue/button';

export default defineComponent({
  name: 'CarComponent',
  components: {
    Dialog,
    Select,
    Button
  },
  setup() {
    const visible = ref(false); 
    const modelFiltered = ref([]);
    const versionFiltered = ref([]);
    const selectedBrand = ref(null);
    const selectedModel = ref(null);
    const selectedVersion = ref(null);
    const carData = ref([]);
    const brandData = ref([]);
    const modelData = ref([]);
    const versionData = ref([]);
    
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
      } catch (error) {
        console.error("Failed to fetch brands:", error);
      }
    };

    const fetchModels = async () => {
      try {
        const response = await axios.get('http://localhost:8080/models');
        modelData.value = response.data; 
      } catch (error) {
        console.error("Failed to fetch models:", error);
      }
    };

    const fetchVersions = async () => {
      try {
        const response = await axios.get('http://localhost:8080/versions');
        versionData.value = response.data; 
      } catch (error) {
        console.error("Failed to fetch versions:", error);
      }
    };

    const openDialogWithBrand = (brand, model, version) => {
      selectedBrand.value = brand;
      selectedModel.value = model;
      selectedVersion.value = version;
      modelFiltered.value = modelData.value.filter(model => model.brand_id === brand.id);
      versionFiltered.value = versionData.value.filter(version => version.model_id === model.id);
      visible.value = true;
    };

    const handleChange = (value) => {
      modelFiltered.value = modelData.value.filter(model => model.brand_id === value.value);
    };

    const handleVersionChange = (value) => {
      console.log(value.value)
      versionFiltered.value = versionData.value.filter(version => version.model_id === value.value);
      console.log(versionFiltered.value)
    }

    fetchCars(); 
    fetchBrands();
    fetchModels();
    fetchVersions();

    return {
      visible,
      carData,
      brandData,
      modelData,
      versionData,
      selectedBrand,
      selectedModel,
      selectedVersion,
      openDialogWithBrand,
      modelFiltered,
      versionFiltered,
      handleChange,
      handleVersionChange
    };
  },
});
</script>

<style scoped>
.car-image {
  width: 500px;
  height: auto;
}
</style>
