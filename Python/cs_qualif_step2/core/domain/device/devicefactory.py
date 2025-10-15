import hashlib
import re
import json
from cs_qualif_step2.core.domain.device.device import Device
from cs_qualif_step2.core.domain.device.device_id import DeviceId
from cs_qualif_step2.core.domain.device.exception.invalid_mac_adress import InvalidMacAddress
from cs_qualif_step2.core.domain.device.exception.invalid_firmware_version import InvalidFirmwareVersion
from cs_qualif_step2.core.domain.device.exception.invalid_time_zone import InvalidTimeZone
from cs_qualif_step2.core.domain.device.exception.invalid_location import InvalidLocation
from cs_qualif_step2.core.application.dto.device_config import DeviceConfig


class DeviceFactory:
    validFirmwareVersions : set[str] = set()
    validTimeZones : set[str] = set()
    validLocations : set[str] = set()

    def __init__(self):
        self.parseDevicesConfigurations()

    def parseDevicesConfigurations(self):
        with open(f'tv_configurations.json', 'r') as file:
            data = json.loads(file.read())
            for element in data['configurations']:
                criterias = element['criteria']
                self.validFirmwareVersions.add(criterias['firmwareVersion'])
                self.validLocations.add(criterias['location'])
                self.validTimeZones.add(criterias['timezone'])
        return

    def create_device(self, device_config: DeviceConfig) -> Device:
        if not re.match(r'^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$', device_config.macAddress):
            raise InvalidMacAddress("Invalid MAC address format")
        
        if not device_config.firmwareVersion in self.validFirmwareVersions:
            raise InvalidFirmwareVersion("Invalid Firmware Version")

        if not device_config.timezone in self.validTimeZones:
            raise InvalidTimeZone("Invalid Time Zone")
        
        if not device_config.location in self.validLocations:
            raise InvalidLocation("Location invalid")

        device_id = DeviceId.generate()

        return Device(
            device_id=device_id,
            macAddress=device_config.macAddress,
            model=device_config.model,
            firmwareVersion=device_config.firmwareVersion,
            serialNumber=device_config.serialNumber,
            displayName=device_config.displayName,
            location=device_config.location,
            timezone=device_config.timezone
        )
