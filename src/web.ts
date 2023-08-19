import { WebPlugin } from '@capacitor/core';

import type { gnssLoggerPlugin } from './definitions';

export class gnssLoggerWeb extends WebPlugin implements gnssLoggerPlugin {
  async startGNSS(): Promise<string> { return 'Log started' }
  async stopGNSS(): Promise<string> { return 'Log stopped' }
}
