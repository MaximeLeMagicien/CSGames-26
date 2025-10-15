from cs_qualif_step2.core.domain.exception.ConflictException import ConflictException

class DeviceNotFoundException(ConflictException):
    def __init__(self, mac_address: str):
        self.mac_address = mac_address
        super().__init__(f"A device with MAC address {mac_address} does not exists.")
