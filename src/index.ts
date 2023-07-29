import { registerPlugin } from '@capacitor/core';

import type { gnssLoggerPlugin } from './definitions';

const gnssLogger = registerPlugin<gnssLoggerPlugin>('gnssLogger', {
  web: () => import('./web').then(m => new m.gnssLoggerWeb()),
});

export * from './definitions';
export { gnssLogger };
