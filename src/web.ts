import { WebPlugin } from '@capacitor/core';

import type { gnssLoggerPlugin } from './definitions';

export class gnssLoggerWeb extends WebPlugin implements gnssLoggerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
