# gnss-logger-plugin

Use the mobile gnss sensor to capture gnss measurements

## Install

```bash
npm install 'path-to-plugin'
```

## Usage

After installing the plug-in, import the definitions from the plug-in source directory into the file you will use
```bash
import { gnssLogger } from 'path-to-plugin/src'
```

## API

<docgen-index>

* [`startGNSS()`](#startgnss)
* [`stopGNSS()`](#stopgnss)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### startGNSS()

```typescript
startGNSS() => Promise<string>
```

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------


### stopGNSS()

```typescript
stopGNSS() => Promise<string>
```

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------

</docgen-api>
