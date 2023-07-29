export interface gnssLoggerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
