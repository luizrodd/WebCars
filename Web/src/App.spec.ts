import { mount, flushPromises } from '@vue/test-utils';
import App from './App.vue';
import { describe, it, expect, vi } from 'vitest';

vi.mock('axios', () => ({
  get: vi.fn(),
  post: vi.fn(),
}));

describe('App', () => {
  it('mounts successfully', async () => {
    const wrapper = mount(App);
    await flushPromises();
    expect(wrapper.exists()).toBe(true);
  });
});