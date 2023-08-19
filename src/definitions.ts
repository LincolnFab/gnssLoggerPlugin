import type { Plugin } from "@capacitor/core/types/definitions";

export interface gnssLoggerPlugin extends Plugin {
  startGNSS(): Promise<string>;
  stopGNSS(): Promise<string>;
}
