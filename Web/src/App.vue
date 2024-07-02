<template>
  <div>
    <ul>
      <li>
        <Button label="Create" @click="openCreate" />
        <Dialog
          v-model:visible="visibleCreate"
          modal
          header="Create"
          :style="{ width: '25rem' }"
        >
          <span class="text-surface-500 dark:text-surface-400 block mb-8"
            >Update your car.</span
          >
          <div class="flex items-center gap-4 mb-4">
            <label for="brand" class="font-semibold w-24">Brand</label>
            <Select
              v-model="createBrand"
              :options="brandData"
              optionLabel="name"
              optionValue="id"
              class="w-full md:w-56"
              @change="handleChange"
              highlightOnSelect
            />
          </div>
          <div class="flex items-center gap-4 mb-4">
            <label for="model" class="font-semibold w-24">Model</label>
            <Select
              v-model="createModel"
              :options="modelFiltered"
              optionLabel="name"
              optionValue="id"
              class="w-full md:w-56"
              @change="handleVersionChange"
              highlightOnSelect
            />
          </div>
          <div class="flex items-center gap-4 mb-4">
            <label for="version" class="font-semibold w-24">Version</label>
            <Select
              v-model="createVersion"
              :options="versionFiltered"
              optionLabel="name"
              optionValue="id"
              class="w-full md:w-56"
              highlightOnSelect
            />
          </div>
          <div>
            <label for="image" class="font-semibold w-24">Image</label>
            <InputText
              :modelValue="createImage"
              :value="createImage"
              v-model="createImage"
              type="text"
              class="w-full"
            />
          </div>
          <div class="flex justify-end gap-2">
            <Button
              type="button"
              label="Cancel"
              severity="secondary"
              @click="visibleCreate = false"
            />
            <Button type="button" label="Save" @click="create">Create</Button>
          </div>
        </Dialog>
      </li>
      <li class="flex">
        <div v-for="car in carData" :key="car.id" class="m-5">
          <img :src="car.image" alt="Car Image" class="car-image" />
          <div
            class="bg-zinc-900 w-[500px] rounded-sm flex justify-between flex-row items-center"
          >
            <div>
              <h3 class="text-3xl text-white">Brand: {{ car.brand.name }}</h3>
              <p class="text-2xl text-white">
                Model: {{ car.brand.model.name }}
              </p>
              <p class="text-lg text-white">
                Version: {{ car.brand.model.version.name }}
              </p>
            </div>
            <div class="">
              <Button
                label="Tools"
                @click="
                  openDialogWithBrand(
                    car.id,
                    car.image,
                    car.brand,
                    car.brand.model,
                    car.brand.model.version
                  )
                "
              />
              <Dialog
                v-model:visible="visible"
                modal
                header="Car"
                :style="{ width: '25rem' }"
              >
                <span class="text-surface-500 dark:text-surface-400 block mb-8"
                  >Update your car.</span
                >
                <div class="flex items-center gap-4 mb-4">
                  <label for="brand" class="font-semibold w-24">Brand</label>
                  <Select
                    v-model="selectedBrand.id"
                    :options="brandData"
                    optionLabel="name"
                    optionValue="id"
                    class="w-full md:w-56"
                    @change="handleChange"
                    highlightOnSelect
                  />
                </div>
                <div class="flex items-center gap-4 mb-4">
                  <label for="model" class="font-semibold w-24">Model</label>
                  <Select
                    v-model="selectedModel.id"
                    :options="modelFiltered"
                    optionLabel="name"
                    optionValue="id"
                    class="w-full md:w-56"
                    @change="handleVersionChange"
                    highlightOnSelect
                  />
                </div>
                <div class="flex items-center gap-4 mb-4">
                  <label for="version" class="font-semibold w-24"
                    >Version</label
                  >
                  <Select
                    v-model="selectedVersion.id"
                    :options="versionFiltered"
                    optionLabel="name"
                    optionValue="id"
                    class="w-full md:w-56"
                    highlightOnSelect
                  />
                </div>
                <div>
                  <label for="image" class="font-semibold w-24">Image</label>
                  <InputText
                    :modelValue="image"
                    :value="image"
                    v-model="image"
                    type="text"
                    class="w-full"
                  />
                </div>
                <div class="flex justify-end gap-2">
                  <Button
                    type="button"
                    label="Cancel"
                    severity="secondary"
                    @click="closeDialog"
                  />
                  <Button type="button" label="Save" @click="update"
                    >Update</Button
                  >
                  <Button type="button" label="Delete" @click="deleteCar"
                    >Delete</Button
                  >
                </div>
              </Dialog>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import axios from "axios";
import Dialog from "primevue/dialog";
import Select from "primevue/select";
import Button from "primevue/button";
import InputText from "primevue/inputtext";

export default defineComponent({
  name: "CarComponent",
  components: {
    Dialog,
    Select,
    Button,
  },
  setup() {
    const visible = ref(false);
    const visibleCreate = ref(false);
    const modelFiltered = ref([]);
    const versionFiltered = ref([]);
    const selectedBrand = ref(null);
    const selectedModel = ref(null);
    const selectedVersion = ref(null);

    const createModel = ref(null);
    const createVersion = ref(null);
    const createBrand = ref(null);
    let createImage = ref("");

    let image = ref("");
    let id = ref(null);
    const carData = ref([]);
    const brandData = ref([]);
    const modelData = ref([]);
    const versionData = ref([]);

    const create = async () => {
      try {
        await axios
          .post("http://localhost:8080/cars/", {
            description: "New Car",
            image: createImage.value,
            brand_id: createBrand.value,
            model_id: createModel.value,
            version_id: createVersion.value,
          })
          .then(() => {
            createImage.value = "";
            createBrand.value = null;
            createModel.value = null;
            createVersion.value = null;
          });
        fetchCars();
        visibleCreate.value = false;
      } catch (error) {
        console.error("Failed to create car:", error);
      }
    };

    const update = async () => {
      try {
        await axios.put(`http://localhost:8080/cars/${id.value}`, {
          description: "Updated Car",
          image: image.value,
          brand_id: selectedBrand.value.id,
          model_id: selectedModel.value.id,
          version_id: selectedVersion.value.id,
        });
        visible.value = false;
        fetchCars();
      } catch (error) {
        console.error("Failed to update car:", error);
      }
    };

    const deleteCar = async () => {
      try {
        await axios.delete(`http://localhost:8080/cars/${id.value}`);
        visible.value = false;
        fetchCars();
      } catch (error) {
        console.error("Failed to delete car:", error);
      }
    };

    const fetchCars = async () => {
      try {
        const response = await axios.get("http://localhost:8080/cars");
        carData.value = response.data;
      } catch (error) {
        console.error("Failed to fetch cars:", error);
      }
    };

    const fetchBrands = async () => {
      try {
        const response = await axios.get("http://localhost:8080/brands");
        brandData.value = response.data;
      } catch (error) {
        console.error("Failed to fetch brands:", error);
      }
    };

    const fetchModels = async () => {
      try {
        const response = await axios.get("http://localhost:8080/models");
        modelData.value = response.data;
      } catch (error) {
        console.error("Failed to fetch models:", error);
      }
    };

    const fetchVersions = async () => {
      try {
        const response = await axios.get("http://localhost:8080/versions");
        versionData.value = response.data;
      } catch (error) {
        console.error("Failed to fetch versions:", error);
      }
    };

    const closeDialog = () => {
      visible.value = false;
    };

    const openDialogWithBrand = (carId, images, brand, model, version) => {
      selectedBrand.value = brand;
      selectedModel.value = model;
      selectedVersion.value = version;
      modelFiltered.value = modelData.value.filter(
        (model) => model.brand_id === brand.id
      );
      versionFiltered.value = versionData.value.filter(
        (version) => version.model_id === model.id
      );
      id.value = carId;
      image.value = images;
      visible.value = true;
    };

    const openCreate = () => {
      visibleCreate.value = true;
    };

    const handleChange = (value) => {
      modelFiltered.value = modelData.value.filter(
        (model) => model.brand_id === value.value
      );
      versionFiltered.value = [];
    };

    const handleVersionChange = (value) => {
      versionFiltered.value = versionData.value.filter(
        (version) => version.model_id === value.value
      );
    };

    fetchCars();
    fetchBrands();
    fetchModels();
    fetchVersions();

    return {
      visible,
      create,
      createImage,
      closeDialog,
      openCreate,
      visibleCreate,
      carData,
      update,
      brandData,
      createBrand,
      createModel,
      createVersion,
      image,
      modelData,
      deleteCar,
      versionData,
      selectedBrand,
      selectedModel,
      selectedVersion,
      openDialogWithBrand,
      id,
      modelFiltered,
      versionFiltered,
      handleChange,
      handleVersionChange,
    };
  },
});
</script>

<style scoped>
.car-image {
  width: 500px;
  height: 300px;
}
</style>
