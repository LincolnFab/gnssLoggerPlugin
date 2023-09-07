import type { Plugin } from '@capacitor/core'

export interface gnssLoggerPlugin extends Plugin {
  startGNSS(): Promise<any>;
  stopGNSS(): Promise<any>;
}
