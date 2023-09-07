import { WebPlugin } from '@capacitor/core';

import type { gnssLoggerPlugin } from './definitions';

export class gnssLoggerWeb extends WebPlugin implements gnssLoggerPlugin {
  async startGNSS(): Promise<any> { return 'Log started' }
  async stopGNSS(): Promise<any> { return 'Log stopped' }
}
