from dataclasses import dataclass

@dataclass
class DeviceConfig:
    macAddress: str
    model: str
    firmwareVersion: str
    serialNumber: str
    displayName: str
    location: str
    timezone: str
