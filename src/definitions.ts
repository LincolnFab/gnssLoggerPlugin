export interface gnssLoggerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  startGNSS(options: { value: string }): Promise<{ value: string }>;
  stopGNSS(options: { value: string }): Promise<{ value: string }>;
}
